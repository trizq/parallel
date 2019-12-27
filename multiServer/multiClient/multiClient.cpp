#include <WinSock2.h>
#include <iostream>
#include <fstream>
#include "CBlockingSocket.h"

using namespace std;
BYTE filename[100] = { "C:\\Users\\Administrator\\Desktop\\12.pdf" };
char filename2[100] = { "C:\\Users\\Administrator\\Desktop\\text8.pdf" };
char serverIP[100] = { 0 };
char serverPORT[100] = { 0 };
HANDLE ghWriteEvent;
HANDLE ghMutex;


DWORD WINAPI clientthread(LPVOID lpParameter) {
	
	
	CBlockingSocket BlockingSocket = CBlockingSocket();
	BlockingSocket.Open(serverIP, serverPORT);
	cout << "Connection established to remote Server at " << serverIP << ":" << serverPORT << endl;
	WaitForSingleObject(ghWriteEvent, INFINITE);
	BlockingSocket.Send(filename, 100);
	cout << "  Receiving file " << filename << endl;
	fstream fs;
	char recvBuf[101] = { 0 };
	fs.open(filename2, ios::out | ios::binary);
	int filerecvd = 0;
	while (filerecvd != -1)
	{
		filerecvd = BlockingSocket.Recv(recvBuf, sizeof(recvBuf));
		//cout << "  Received " << filerecvd << " bytes." << endl;
		fs.write(recvBuf, filerecvd);
	}
	return 0;
	//CBlockingSocket *cs = (CBlockingSocket *)lpParameter;
	//CBlockingSocket blockingSocket = *cs;//用局部变量保存线程传进来的地址传递的参数，防止主线程中socket被改写。
	//fstream fs;
	//char recvBuf[101] = { 0 };
	//fs.open(filename2, ios::out | ios::binary);
	//int filerecvd = 0;
	//while (filerecvd != -1)
	//{
	//	filerecvd = blockingSocket.Recv(recvBuf, sizeof(recvBuf));
	//	cout << "  Received " << filerecvd << " bytes." << endl;
	//	fs.write(recvBuf, filerecvd);
	//}
	//return 0;
}




int main()
{
	int is_con;
	ghWriteEvent = CreateEvent(
		NULL,               // default security attributes
		TRUE,               // manual-reset event
		FALSE,              // initial state is nonsignaled
		TEXT("WriteEvent")  // object name
	);
//	CBlockingSocket BlockingSocket;
 
	    is_con = 1;
		//cout << is_con <<endl;
		while (is_con)
		{
			cout << "是否要连接服务器（1/0）？" << endl;
			cin >> is_con;
			//cout << is_con<<endl;
			if (is_con == 1)
			{
				//cout << "进入程序"<<endl;
				//serverIP[100] = { 0 };
				//serverPORT[100] = { 0 };
				cout << "输入服务器IP：" << endl;
				cin >> serverIP;
				//cout<< serverIP<<endl;
				cout << "输入服务器端口：" << endl;
				cin >> serverPORT;
				//cout << serverPORT << endl;
				HANDLE hThread;
	            hThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)clientthread, 0, 0, NULL);
				//SetEvent(ghWriteEvent);
				
			}
			else
			{
				cout << "赋值给信号量..." << endl;
				SetEvent(ghWriteEvent);
				break;
			}
			
		}
	
		
	//HANDLE hThread;
	//hThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)clientthread, &BlockingSocket, 0, NULL);
	return 0;
}


	