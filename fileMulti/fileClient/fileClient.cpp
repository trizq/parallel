#include <WinSock2.h>
#include <iostream>
#include <fstream>
#include "CBlockingSocket.h"
#include <windows.h>
#include <stdio.h>

using namespace std;


char filename2[100] = { "C:\\Users\\Administrator\\Desktop\\456.txt" };
char serverIP[100] = { "10.64.132.70" };
char serverPORT[100] = { 0 };
int serverPORT2 = 7777;
char conNum[100] = { 0 };
int m = 0;
//char serverPORT_1[100] = { "7777"};
//char serverPORT_2[100] = { "8888" };
//char serverPORT_3[100] = { "9999" };
int i;
HANDLE ghMutex;
fstream fs;
int port = 7777;

DWORD WINAPI recthread(LPVOID lpParameter) {
	CBlockingSocket *cs = (CBlockingSocket *)lpParameter;
	CBlockingSocket blockingSocket = *cs;//用局部变量保存线程传进来的地址传递的参数，防止主线程中socket被改写。
	cout << "进入数据线程" << endl;
	char recvBuf[101] = { 0 };
	int filerecvd = 0;
	while (filerecvd != -1)
	{
		filerecvd = blockingSocket.Recv(recvBuf, sizeof(recvBuf));
		//cout << "  Received " << filerecvd << " bytes." << endl;
		fs.write(recvBuf, filerecvd);
	}
	return 0;
}

DWORD WINAPI clientthread(LPVOID lpParameter) {

	_itoa(serverPORT2, serverPORT, 10);
	cout << "Connection established to remote Server at " << serverIP << ":" << serverPORT << endl;
	//WaitForSingleObject(ghWriteEvent, INFINITE);
	//BlockingSocket.Send(filename, 100);


	CBlockingSocket BlockingSocket = CBlockingSocket();
	BlockingSocket.Open(serverIP, serverPORT);
	cout << "Begin  receiving file... " << endl;
	_itoa(i, conNum, 10);
	cout << "conNum的值是：" << conNum << endl;

	BlockingSocket.Send(conNum, 100);

	WaitForSingleObject(
		ghMutex,    // handle to mutex
		INFINITE);

	//HANDLE hThread2;
	//hThread2 = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)recthread, &BlockingSocket, 0, NULL);
	char recvBuf[101] = { 0 };
	int filerecvd = 0;
	//cout << "data from" << GetCurrentThreadId() << endl;
	while (filerecvd != -1)
	{
		filerecvd = BlockingSocket.Recv(recvBuf, sizeof(recvBuf));
		//cout << "  Received " << filerecvd << " bytes." << endl;
		fs.write(recvBuf, filerecvd);

	}
	//cout << "Receiving over... " << endl;
	return 0;

}



int main(void) {
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
	fs.open(filename2, ios::out | ios::binary);

	for (i = 0; i < 3; i++)
	{



		aThread[i] = CreateThread(
			NULL,       // default security attributes
			0,          // default stack size
			(LPTHREAD_START_ROUTINE)clientthread,
			NULL,       // no thread function arguments
			0,          // default creation flags
			&ThreadID); // receive thread identifier

		if (aThread[i] == NULL)
		{
			printf("CreateThread error: %d\n", GetLastError());
			return 1;
		}

		Sleep(200);
		serverPORT2++;
	}
	WaitForMultipleObjects(3, aThread, TRUE, INFINITE);
	printf("线程建立结束");
	Sleep(5000);
	for (i = 0; i < 3; i++)
		CloseHandle(aThread[i]);
	CloseHandle(ghMutex);

}