var PROTO_PATH = './proto/math_message.proto';
var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
     longs: String,
     enums: String,
     defaults: true,
     oneofs: true
    });
var hello_proto = grpc.loadPackageDefinition(packageDefinition);

function main() {
    var client = new hello_proto.MathService('localhost:50051',
                                         grpc.credentials.createInsecure());
    client.sum({a: 1.0, b: 2}, function(err, response) {
        if(err){
            console.log(err)
        }
    console.log('Greeting:', response);
  });
  }

  main()