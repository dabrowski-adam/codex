//> using scala 3.5.1
//> using dep com.lihaoyi::os-lib:0.10.7

import scala.util.Properties


val platformSuffix: String =
    if      Properties.isWin   then "windows"
    else if Properties.isLinux then "linux"
    else if Properties.isMac   then "mac"
    else
        sys.error(s"Unrecognized OS: ${sys.props("os.name")}")

val artifactsPath = os.Path("artifacts", os.pwd)

val destPath =
    if Properties.isWin then
        artifactsPath / s"codex-$platformSuffix.exe"
    else
        artifactsPath / s"codex-$platformSuffix"

val scalaCLILauncher =
    if Properties.isWin then "scala-cli.bat" else "scala-cli"


os.makeDir(artifactsPath)

os.proc(scalaCLILauncher,"--power",  "package", ".", "-o", destPath, "--native-image")
    .call(cwd = os.pwd)
    .out
    .text()
    .trim
