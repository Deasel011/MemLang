# -*- coding: utf-8 -*-
import socket
from memorpy import *

HOST = ''
PORT = 51337
#create an INET, STREAMing socket
serversocket = socket.socket(
    socket.AF_INET, socket.SOCK_STREAM)
#bind the socket to a public host,
# and a well-known port
serversocket.bind((HOST, PORT))
#become a server socket
serversocket.listen(5)


#memorpy variables
mw=None
lo=None

while 1:
    #accept connections from outside
    (clientsocket, address) = serversocket.accept()
    #now do something with the clientsocket
    #in this case, we'll pretend this is a threaded server
    data = clientsocket.recv(1024)
    print data
    if data[:5] == "open:":
        print "Gotta open ", data[5:]
        mw=MemWorker(pid=int(data[5:]))
        print mw

    if data[:5] == "find:":
        print "Gotta find ", data[5:]
        None

    if data[:5] == "loca:":
        print "Gotta locate ", data[5:]
        None

    if data[:5] == "swap:":
        print "Gotta swap ", data[5:]
        None

    clientsocket.close()

