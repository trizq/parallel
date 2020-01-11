# parallel
Parallel computing job, multi-threaded file download.
1、使用多线程编写一个多客户端可以同时从服务器下载文件的小程序。主要需要实现的是服务器段，客户端可通过nc来模拟（多开几个进程）。
2、客户端可以从多个服务器端并行下载同一个文件。分片，拼接。会涉及到多个线程或者多个程序同时写入一个文件的情况，需要互斥。
3、pi
