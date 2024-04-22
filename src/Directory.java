package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a directory in a file system.
 * Extends the {@link FileSystemElement} class.
 */
public class Directory extends FileSystemElement {
  /**
   * The list of children elements in the directory.
   */
  private List<FileSystemElement> children;

  /**
   * Constructs a new Directory object with the specified name and parent
   * directory.
   *
   * @param name   the name of the directory
   * @param parent the parent directory of the directory
   */
  public Directory(final String name, final Directory parent) {
    super(name, parent);
    children = new ArrayList<>();
  }

  /**
   * Returns the list of children elements in the directory.
   *
   * @return the list of children elements
   */
  public List<FileSystemElement> getChildren() { return children; }

  /**
   * Sets the list of children elements in the directory.
   *
   * @param children the list of children elements
   */
  public void setChildren(final List<FileSystemElement> children) {
    this.children = children;
  }

  /**
   * Adds a new file system element to the directory.
   *
   * @param e the file system element to add
   * @return true if the element was added successfully, false otherwise
   */
  public boolean add(final FileSystemElement e) {
    if (find(e.getName()) != null) {
      return false;
    }
    return children.add(e);
  }

  /**
   * Removes the file system element at the specified index from the directory.
   *
   * @param index the index of the element to remove
   * @return the removed file system element
   */
  public FileSystemElement remove(final int index) {
    return children.remove(index);
  }

  /**
   * Removes the specified file system element from the directory.
   *
   * @param e the file system element to remove
   * @return true if the element was removed successfully, false otherwise
   */
  public boolean remove(final FileSystemElement e) {
    return children.remove(e);
  }

  /**
   * Finds and returns the file system element with the specified name in the
   * directory.
   *
   * @param name the name of the element to find
   * @return the found file system element, or null if not found
   */
  public FileSystemElement find(final String name) {
    for (final FileSystemElement child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  /**
   * Recursively finds and returns the file system element with the specified
   * path in the directory.
   *
   * @param pathParts the path parts of the element to find
   * @return the found file system element, or null if not found
   */
  @Override
  public FileSystemElement find(final String[] pathParts) {
    if (pathParts.length == 0)
      return this;
    if (getParent() == null && !pathParts[0].equals(""))
      return null;
    else if (getParent() != null && !pathParts[0].equals(getName()))
      return null;
    if (pathParts.length == 1)
      return this;

    for (final FileSystemElement child : children) {
      final FileSystemElement found =
          child.find(Arrays.copyOfRange(pathParts, 1, pathParts.length));
      if (found != null)
        return found;
    }

    return null;
  }

  /**
   * Prints the children elements of the directory.
   */
  public void printChildren() {
    if (children.isEmpty()) {
      System.out.println("Empty directory");
      return;
    }
    for (final FileSystemElement child : children) {
      if (child instanceof Directory)
        System.out.println(child);
    }
    for (final FileSystemElement child : children) {
      if (child instanceof File)
        System.out.println(child);
    }
  }

  /**
   * Deletes the file system element with the specified name from the directory.
   *
   * @param name the name of the element to delete
   * @throws IllegalArgumentException if the element is not found
   */
  public void delete(final String name) {
    final FileSystemElement child = find(name);
    if (child != null) {
      if (child instanceof Directory)
        System.out.println("Deleting directory " + child);
      else if (child instanceof File)
        System.out.println("Deleting file " + child);
      child.delete();
    } else {
      throw new IllegalArgumentException("Element not found");
    }
  }

  /**
   * Deletes the directory and all its children elements recursively.
   */
  public void delete() {
    final List<FileSystemElement> childrenCopy = new ArrayList<>(children);
    for (final FileSystemElement child : childrenCopy) {
      child.delete();
    }
    super.delete();
  }

  /**
   * Returns a string representation of the directory.
   *
   * @return a string representation of the directory
   */
  @Override
  public String toString() {
    return "* " + super.toString() + "/";
  }

  /**
   * Recursively finds and returns the file system element with the specified
   * name in the directory and its children.
   *
   * @param name the name of the element to find
   * @return the found file system element, or null if not found
   */
  @Override
  public FileSystemElement recFind(final String name) {
    if (getName().equals(name)) {
      return this;
    }
    for (final FileSystemElement child : children) {
      final FileSystemElement found = child.recFind(name);
      if (found != null) {
        return found;
      }
    }
    return null;
  }

  /**
   * Prints the directory and its children elements in a tree-like structure.
   *
   * @param level the indentation level of the directory
   */
  @Override
  public void printTree(final int level) {
    super.printTree(level);
    for (final FileSystemElement child : children) {
      child.printTree(level + 1);
    }
  }

  /**
   * Sorts the children elements of the directory.
   */
  public void sort() { Collections.sort(children); }
}
