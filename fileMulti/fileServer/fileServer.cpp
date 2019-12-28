
#include <WinSock2.h>
#include <iostream>
#include <fstream>
#include <process.h>
#include "CBlockingSocket.h"


using namespace std;
BYTE filename[100] = { "C:\\Users\\Administrator\\Desktop\\123.txt" };
char serverPORT[100] = { 0 };
int i;
int num = 0;
HANDLE ghMutex;
int fileLength;

DWORD WINAPI serverthread(LPVOID lpParameter)
{
	CBlockingSocket *cs = (CBlockingSocket *)lpParameter;
	CBlockingSocket ClientSocket = *cs;//用局部变量保存线程传进来的地址传递的参数，防止主线程中socket被改写。

	//if (ClientSocket.Read(filename, 100) != -1)
	//{
		cout << "  File requested from the client: " << filename << endl;
		WaitForSingleObject(
				ghMutex,    // handle to mutex
				INFINITE);
		ifstream infile((char *)filename, ios::in | ios::binary);
		infile.seekg(0, ios::end);
		 fileLength = infile.tellg();
		 fileLength = fileLength / 3;
		 infile.seekg(0 + num * fileLength, ios::beg);
		//cout << "文件总长度:" << fileLength << endl;
		//cout << "文件长度（int）:" << i* fileLength/3 << endl;
		//cout <<"文件长度（float）:" <<(float)fileLength/3 << endl;
		
		infile.seekg(0+ num * fileLength, ios::beg);
		//infile.seekg(0, ios::beg + num * fileLength);
		num++;
		cout <<"num大小："<< num << endl;
		bool flag = true;
		
		if (!infile)
		{
			cout << "Open file failed!" << endl;
		}
		else
		{
			BYTE file[101] = { 0 };
			while (fileLength >= 100)
			{
				infile.read((char *)file, 100);
				file[100] = '\0';
				flag = ClientSocket.Send(file, 100);
				fileLength -= 100;
			}
			infile.read((char *)file, fileLength);
			file[fileLength] = '\0';
			flag = ClientSocket.Send(file, fileLength);
		}
		if (flag)
		{
			cout << "  Send file back to the client: " << filename << endl;
		}
//	}

	ClientSocket.Close();
	return 0;
}

DWORD WINAPI cserverthread(LPVOID lpParameter) {
	CBlockingSocket ListenSocket;
	CBlockingSocket::Initialize();
	ListenSocket.HintsAndResult(NULL, serverPORT);
	ListenSocket.Open();
	ListenSocket.Bind();
	ListenSocket.Listen(serverPORT);
	cout << "FileServer is listening on port:" << serverPORT << endl;

	while (1)
	{
		cout << "Waiting for a connection…" << endl;

		struct sockaddr their_addr;
		struct sockaddr_in their_addrin;
		CBlockingSocket ClientSocket = ListenSocket.Accept(their_addr);
		memcpy(&their_addrin, &their_addr, sizeof(their_addr));
		cout << "Accepted connection from " << inet_ntoa(their_addrin.sin_addr) << endl;

		HANDLE hThread;
		hThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)serverthread, &ClientSocket, 0, NULL);

	}




	ListenSocket.Close();
	CBlockingSocket::Cleanup();
}

int main(int argc, char *argv[])
{
	HANDLE aThread[3];
	DWORD ThreadID;

	ghMutex = CreateMutex(
			NULL,              // default security attributes
			FALSE,             // initially not owned
			NULL);             // unnamed mutex

		if (ghMutex == NULL)
		{
			printf("CreateMutex error: %d\n", GetLastError());
			return 1;
		}
		
	for (i = 0; i < 3; i++)
	{
		Sleep(800);
		cout << "输入端口号：" << endl;
		cin >> serverPORT;

		aThread[i] = CreateThread(
			NULL,       // default security attributes
			0,          // default stack size
			(LPTHREAD_START_ROUTINE)cserverthread,
			NULL,       // no thread function arguments
			0,          // default creation flags
			&ThreadID); // receive thread identifier

		if (aThread[i] == NULL)
		{
			printf("CreateThread error: %d\n", GetLastError());
			return 1;
		}
	}

	WaitForMultipleObjects(3, aThread, TRUE, INFINITE);
	printf("线程结束");

	for (i = 0; i < 3; i++)
		CloseHandle(aThread[i]);
	return 0;
}
