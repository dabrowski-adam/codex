//> using scala 3.5.1

import scala.sys.process.Process

val latestCommit = Process("git --no-pager log --pretty=format:'%s' -1").lazyLines.lastOption

val latestTag =
  for
    message <- latestCommit
    if message.contains(':')
    meta     = message.takeWhile(_ != ':')
    tag      = meta.takeWhile(_ != '(')
    //scope      = Option(meta)
    //    .filter(_.contains('(')).map(_.dropWhile(_ != '(')).drop(1)
    //    .filter(_.contains(')')).map(_.takeWhile(_ != ')'))
  yield tag


println(latestTag.getOrElse(""))
