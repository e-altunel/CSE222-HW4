package src;

/**
 * Represents a file in a file system.
 */
public class File extends FileSystemElement {
  /**
   * Constructs a new File object with the specified name and parent directory.
   *
   * @param name   the name of the file
   * @param parent the parent directory of the file
   */
  public File(final String name, final Directory parent) {
    super(name, parent);
  }

  /**
   * Finds a file or directory with the specified name in the current directory.
   * If the file or directory is found, it is returned; otherwise, null is
   * returned.
   *
   * @param pathParts the path parts to search for
   * @return the found file or directory, or null if not found
   */
  @Override
  public FileSystemElement find(final String[] pathParts) {
    if (pathParts.length != 1)
      return null;
    if (pathParts[0].equals(getName()))
      return this;
    return null;
  }

  /**
   * Recursively finds a file or directory with the specified name in the
   * current directory. If the file or directory is found, it is returned;
   * otherwise, null is returned.
   *
   * @param name the name of the file or directory to find
   * @return the found file or directory, or null if not found
   */
  @Override
  public FileSystemElement recFind(final String name) {
    if (name.equals(getName()))
      return this;
    return null;
  }
}
