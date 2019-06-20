#!/usr/bin/env node
const exec = require('child_process').exec
// const spawn = require('child_process').spawn
const isWin = process.platform === "win32"
const separator = isWin ? ";" : ":"
const cp = `-cp ".${separator}${__dirname}/json-simple.jar"`


// const child = spawn(
//   `java`,
//   [
//     // cp,
//     '-cp',`".${separator}${__dirname}/json-simple.jar"`,
//     `${__dirname}/demo/platformer/Main`],
//   { stdio: [
//       process.stdin, process.stdout, process.stderr
//     ]
//   })
//
//
//
// child.on('exit',()=> process.exit(0))
// child.on('error',(err)=>{
//   throw err;
//   process.exit(1);
// })

exec(`java ${cp} demo/platformer/Main`,(error, stdout, stderr) => {
    console.log("Running platformer")
    if (error !== null) {
      console.log(stderr)
      throw new Error(stderr)
      process.exit(1)
    }
    console.log(stdout)
    process.exit(0)
});
