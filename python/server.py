#!/usr/bin/python3
from http.server import BaseHTTPRequestHandler, HTTPServer
import random
import time

hostName = "localhost"
serverPort = 8080

class MyServer(BaseHTTPRequestHandler):
    numberwang = random.randint(0, 10)

    def __init__(self, request, client_address, server):
        BaseHTTPRequestHandler.__init__(self, request, client_address, server)

    # do_GET is run in response to a get request to the webserver.
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "application/json")
        self.end_headers()

        # http://localhost:8080/guess?number=<path_int>
        path_int = int(self.path.split('=')[1])

        self.wfile.write(bytes('{}'.format(path_int == self.numberwang), "utf-8"))

if __name__ == "__main__":        
    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")
