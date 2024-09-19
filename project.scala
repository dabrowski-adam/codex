//> using scala 3.5.1
//> using jvm graalvm-community:22.0.2

//> using packaging.packageType graalvm
//> using packaging.graalvmArgs --no-fallback
//> using packaging.output target/codex

//> using options -deprecation -feature -language:strictEquality
//> using options -Xkind-projector:underscores -Xmax-inlines:64
//> using options -Yexplicit-nulls
//> using options -Wnonunit-statement -Wsafe-init -Wshadow:all

//> using dependency org.typelevel::cats-core:2.12.0
//> using dependency org.typelevel::cats-effect:3.5.4
//> using dependency co.fs2::fs2-core:3.11.0
//> using dependency co.fs2::fs2-io:3.11.0

//> using test.dependency com.disneystreaming::weaver-cats:0.8.4
