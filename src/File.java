package src;

public class File extends FileSystemElement {
  public File(final String name, final Directory parent) {
    super(name, parent);
  }

  @Override
  public FileSystemElement find(String[] pathParts) {
    if (pathParts.length != 1)
      return null;
    if (pathParts[0].equals(getName()))
      return this;
    return null;
  }

  @Override
  public FileSystemElement recFind(String name) {
    if (name.equals(getName()))
      return this;
    return null;
  }
}
