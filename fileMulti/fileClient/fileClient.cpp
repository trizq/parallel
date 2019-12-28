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
//char serverPORT_1[100] = { "7777"};
//char serverPORT_2[100] = { "8888" };
//char serverPORT_3[100] = { "9999" };
int i;
//HANDLE ghMutex;
fstream fs;

DWORD WINAPI recthread(LPVOID lpParameter) {
	CBlockingSocket *cs = (CBlockingSocket *)lpParameter;
	CBlockingSocket blockingSocket = *cs;//�þֲ����������̴߳������ĵ�ַ���ݵĲ�������ֹ���߳���socket����д��
	cout << "���������߳�" << endl;
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

	
	cout << "Connection established to remote Server at " << serverIP << ":" << serverPORT << endl;
	//WaitForSingleObject(ghWriteEvent, INFINITE);
	//BlockingSocket.Send(filename, 100);
	cout << "Begin  receiving file... "  << endl;
	CBlockingSocket BlockingSocket = CBlockingSocket();
	BlockingSocket.Open(serverIP, serverPORT);
	//WaitForSingleObject(
	//	ghMutex,    // handle to mutex
	//	INFINITE);
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
	return 0;

}



int main(void) {
	HANDLE aThread[3];
	DWORD ThreadID;

	//ghMutex = CreateMutex(
	//	NULL,              // default security attributes
	//	FALSE,             // initially not owned
	//	NULL);             // unnamed mutex

	//if (ghMutex == NULL)
	//{
	//	printf("CreateMutex error: %d\n", GetLastError());
	//	return 1;
	//}
	fs.open(filename2, ios::out | ios::binary);
	
	for ( i = 0; i < 3; i++)
	{
		Sleep(800);
		cout << "����˿ںţ�" << endl;
		cin >> serverPORT;
		
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
	}
	WaitForMultipleObjects(3, aThread, TRUE, INFINITE);
	printf("�߳̽�������");

	for (i = 0; i < 3; i++)
		CloseHandle(aThread[i]);
	//CloseHandle(ghMutex);

}