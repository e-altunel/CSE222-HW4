package src;

import java.util.Date;

/**
 * Represents a file system element.
 */
public abstract class FileSystemElement
    implements Comparable<FileSystemElement> {
  /**
   * The name of the file system element.
   */
  private String name;
  /**
   * The creation date of the file system element.
   */
  private Date creationDate;
  /**
   * The parent directory of the file system element.
   */
  private Directory parent;

  /**
   * Constructs a new FileSystemElement object with the specified name and
   * parent directory.
   *
   * @param name   the name of the file system element
   * @param parent the parent directory of the file system element
   */
  public FileSystemElement(final String name, final Directory parent) {
    this.name = name;
    this.creationDate = new Date();
    this.parent = parent;
  }

  /**
   * Returns the name of the file system element.
   *
   * @return the name
   */
  public String getName() { return name; }

  /**
   * Sets the name of the file system element.
   *
   * @param name the name
   */
  public void setName(final String name) { this.name = name; }

  /**
   * Returns the creation date of the file system element.
   *
   * @return the creation date
   */
  public Date getCreationDate() { return creationDate; }

  /**
   * Sets the creation date of the file system element.
   *
   * @param creationDate the creation date
   */
  public void setCreationDate(final Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Returns the parent directory of the file system element.
   *
   * @return the parent directory
   */
  public Directory getParent() { return parent; }

  /**
   * Sets the parent directory of the file system element.
   *
   * @param parent the parent directory
   */
  public void setParent(final Directory parent) { this.parent = parent; }

  /**
   * Prints the path of the file system element.
   */
  public void printPath() {
    if (parent != null) {
      parent.printPath();
      System.out.print("/" + getName());
    }
  }

  /**
   * Finds a file or directory with the specified name in the current directory.
   * If the file or directory is found, it is returned; otherwise, null is
   * returned.
   *
   * @param pathParts the path parts to search for
   * @return the found file or directory, or null if not found
   */
  public abstract FileSystemElement find(String[] pathParts);

  /**
   * Recursively finds a file or directory with the specified path. If the file
   * or directory is found, it is returned; otherwise, null is returned.
   *
   * @param pathParts the path parts to search for
   * @return the found file or directory, or null if not found
   */
  public Directory findDirectory(final String[] pathParts) {
    return (Directory)find(pathParts);
  }

  /**
   * Recursively finds a file with the specified path.
   * If the file is found, it is returned; otherwise, null is returned.
   *
   * @param pathParts the path parts to search for
   * @return the found file, or null if not found
   */
  public File findFile(final String[] pathParts) {
    return (File)find(pathParts);
  }

  /**
   * Deletes the file system element.
   */
  public void delete() {
    if (parent == null)
      return;
    parent.remove(this);
    setParent(null);
  }

  /**
   * Moves the file system element to the specified directory.
   *
   * @param newParent the new parent directory
   */
  public void move(final Directory newParent) {
    if (parent == null)
      return;
    parent.remove(this);
    setParent(newParent);
    newParent.add(this);
  }

  /**
   * Returns a string representation of the file system element.
   *
   * @return the string representation
   */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Recursively finds and returns the file system element with the specified
   * name in the directory and its children.
   *
   * @param name the name of the element to find
   * @return the found file system element, or null if not found
   */
  public abstract FileSystemElement recFind(String name);

  /**
   * Prints the file system element as a tree with the specified level.
   *
   * @param level the level of the tree
   */
  public void printTree(final int level) {
    for (int i = 0; i < level; i++)
      System.out.print("  ");
    System.out.println(this);
  }

  /**
   * Compares this file system element with the specified file system element
   * for order. Returns a negative integer, zero, or a positive integer as this
   * file system element is less than, equal to, or greater than the specified
   * file system element.
   *
   * @param other the file system element to compare
   * @return a negative integer, zero, or a positive integer as this file system
   * element is less than, equal to, or greater than the specified file system
   * element
   */
  @Override
  public int compareTo(final FileSystemElement other) {
    return name.compareTo(other.getName());
  }
}
