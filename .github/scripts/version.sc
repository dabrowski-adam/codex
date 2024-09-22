//> using scala 3.5.1

import scala.sys.process.Process


opaque type Version = (Int, Int, Int)

extension (version: Version)
    def major: Int = version._1
    def minor: Int = version._2
    def patch: Int = version._3
    
    def show: String = s"$major.$minor.$patch"
    
    def isLts: Boolean = major % 2 == 1

/**
 * Determine the current version based on git commits marked with “major:”, “minor:” or “patch:”.
 */
def current: String =
    val commits = Process("git --no-pager log --reverse --pretty=format:'%s'").lazyLines
    
    commits.foldLeft((0, 0, 0))(version).show


def version(lastVersion: Version, commit: String): Version =
    val tag      = commit.takeWhile(x => x != '(' && x != ':')
    val afterLts = lastVersion.isLts
    
    tag match
        case "major"             => (lastVersion.major + 1, 0, 0)
        case "minor" if afterLts => (lastVersion.major + 1, 0, 0)
        case "minor"             => (lastVersion.major, lastVersion.minor + 1, 0)
        case "patch"             => (lastVersion.major, lastVersion.minor, lastVersion.patch + 1)
        case "other"             => (lastVersion.major, lastVersion.minor, lastVersion.patch)
        case unknown             => throw MatchError(s"One of the commit messages begins with “$unknown” which is not a recognised commit type.")


println(current)
