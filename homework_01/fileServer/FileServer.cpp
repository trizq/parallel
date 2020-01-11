
#include <WinSock2.h>
#include <iostream>
#include <fstream>
#include <process.h>
#include "CBlockingSocket.h"


using namespace std;
char serverPORT[100] = { 0 };
int serverPORT2 = 7777;

DWORD WINAPI serverthread(LPVOID lpParameter)
{
	CBlockingSocket *cs = (CBlockingSocket *)lpParameter;
	CBlockingSocket ClientSocket = *cs;//用局部变量保存线程传进来的地址传递的参数，防止主线程中socket被改写。

	BYTE filename[100] = { 0 };
	if (ClientSocket.Read(filename, 100) != -1)
	{
		cout << "  File requested from the client: " << filename << endl;
		ifstream infile((char *)filename, ios::in | ios::binary);

		infile.seekg(0, ios::end);
		int fileLength = infile.tellg();
		infile.seekg(0, ios::beg);
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
	}

	ClientSocket.Close();
	return 0;
}

int main()
{
	_itoa(serverPORT2, serverPORT, 10);
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
	return 0;
}
