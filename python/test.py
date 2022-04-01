from ast import Assert
import unittest
import requests
from multiprocessing import Process
from http.server import BaseHTTPRequestHandler, HTTPServer
import random
import server

hostName = "localhost"
serverPort = 8081


class TestStringMethods(unittest.TestCase):
    
    def runServer(self):
        webServer = HTTPServer((hostName, serverPort), server.MyServer)
        print("Server started http://%s:%s" % (hostName, serverPort))

        try:
            webServer.serve_forever()
        except KeyboardInterrupt:
            pass

        webServer.server_close()
        print("Server stopped.")
        
    def setUp(self):

        self.process = Process(target=self.runServer)
        self.process.start()
        
    def tearDown(self):
        self.process.terminate()

    def test_numberwang(self):
        r = requests.get('http://{}:{}/guess?number=2'.format(hostName, serverPort))
        self.assertEqual(r.status_code, 200)
        if (r.content.decode("utf-8") != "False") and (r.content.decode("utf-8") != "True"):
            self.fail("Not true and not false")
        

if __name__ == '__main__':
    unittest.main()
