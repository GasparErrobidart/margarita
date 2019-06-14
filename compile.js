#!/usr/bin/env node
const exec = require('child_process').exec
const isWin = process.platform === "win32"
const separator = isWin ? ";" : ":"
const cp = `-cp ".${separator}${__dirname}/json-simple.jar"`
// console.log("cp",cp);

exec(`javac ${cp} configurator/*.java && javac ${cp} puppeteer/*.java && javac ${cp} animaper/*.java && javac ${cp} controller/*.java && javac ${cp} *.java && javac ${cp} *.java && javac ${cp} demo/platformer/*.java`,(error, stdout, stderr) => {

    if (error !== null) {
      console.log(stderr)
      throw new Error(stderr)
      process.exit(1)
    }
    console.log(stdout)
    process.exit(0)
});
