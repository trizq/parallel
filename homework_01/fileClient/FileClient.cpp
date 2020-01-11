#include <WinSock2.h>
#include <iostream>
#include <fstream>
#include "CBlockingSocket.h"

using namespace std;
char clientIP[100] = { "192.168.43.128" };
char clientPORT[100] = { 0 };
int clientPORT2 = 7777;

int main()
{
	_itoa(clientPORT2, clientPORT, 10);
	CBlockingSocket BlockingSocket = CBlockingSocket();
	if (BlockingSocket.Open(clientIP, clientPORT))
	{
		cout << "Connection established to remote Server at " << clientIP << ":" << clientPORT << endl;
		cout << "Input request file path: ";
		BYTE filename[100];
		cin >> filename;
		if (BlockingSocket.Send(filename, 100))
		{
			cout << "  Requesting file on the server: " << filename << endl;

		cout << "==============================================================================" << endl;

			char filename2[100] = { 0 };
			cout << "  Input file save path:";
			cin >> filename2;

			cout << "  Receiving file " << filename << endl;
			fstream fs;
			char recvBuf[101] = { 0 };
			fs.open(filename2, ios::out | ios::binary);
			int filerecvd = 0;
			while (filerecvd != -1)
			{
				filerecvd = BlockingSocket.Recv(recvBuf, sizeof(recvBuf));
				cout << "  Received " << filerecvd << " bytes." << endl;
				fs.write(recvBuf, filerecvd);
			}

		}
	}
	printf("文件下载完毕...");
	Sleep(5000);
	return 0;
}