#!/usr/bin/env node
const exec = require('child_process').exec
const isWin = process.platform === "win32"
const separator = isWin ? ";" : ":"
const cp = `-cp ".${separator}${__dirname}/json-simple.jar"`

// console.log("cp",cp)
// console.log("Shell:",`java ${cp} Main`)

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
