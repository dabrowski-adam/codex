import scala.util.Try
import cats.*
import cats.effect.*
import weaver.SimpleIOSuite


object AppSuite extends SimpleIOSuite:

    pureTest("math works"):
        expect(1 + 1 == 2)
