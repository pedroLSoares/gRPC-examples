var PROTO_PATH = './proto/math_message.proto';
var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');
// Suggested options for similarity to existing grpc.load behavior
var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
     longs: String,
     enums: String,
     defaults: true,
     oneofs: true,
     outdir: "./"
    });
var protoDescriptor = grpc.loadPackageDefinition(packageDefinition);
// The protoDescriptor object has the full package hierarchy
var routeguide = protoDescriptor.routeguide;

/**
 * Implements the SayHello RPC method.
 */
 function x(call, callback) {
    callback(null, {message: 'Hello ' + call.request.name});
  }

function sumValues(call, callback) {
    callback(null, {result: call.request.a + call.request.b})
}


function main() {
    var server = new grpc.Server();
    server.addService(protoDescriptor.MathService.service, {sum: sumValues});
    server.bindAsync('0.0.0.0:50051', grpc.ServerCredentials.createInsecure(), () => {
      server.start();
    });
    console.log("started")
  }

  main();