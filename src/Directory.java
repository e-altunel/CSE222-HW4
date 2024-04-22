package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Directory extends FileSystemElement {
  private List<FileSystemElement> children;

  public Directory(final String name, final Directory parent) {
    super(name, parent);
    children = new ArrayList<>();
  }

  public List<FileSystemElement> getChildren() { return children; }

  public void setChildren(final List<FileSystemElement> children) {
    this.children = children;
  }

  public boolean add(final FileSystemElement e) {
    if (find(e.getName()) != null) {
      return false;
    }
    return children.add(e);
  }

  public FileSystemElement remove(final int index) {
    return children.remove(index);
  }

  public boolean remove(final FileSystemElement e) {
    return children.remove(e);
  }

  public FileSystemElement find(String name) {
    for (FileSystemElement child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  @Override
  public FileSystemElement find(String[] pathParts) {
    if (pathParts.length == 0)
      return this;
    if (getParent() == null && !pathParts[0].equals(""))
      return null;
    else if (getParent() != null && !pathParts[0].equals(getName()))
      return null;
    if (pathParts.length == 1)
      return this;

    for (FileSystemElement child : children) {
      FileSystemElement found =
          child.find(Arrays.copyOfRange(pathParts, 1, pathParts.length));
      if (found != null)
        return found;
    }

    return null;
  }

  public void printChildren() {
    if (children.isEmpty()) {
      System.out.println("Empty directory");
      return;
    }
    for (FileSystemElement child : children) {
      if (child instanceof Directory)
        System.out.println(child);
    }
    for (FileSystemElement child : children) {
      if (child instanceof File)
        System.out.println(child);
    }
  }

  public void delete(String name) {
    FileSystemElement child = find(name);
    if (child != null) {
      if (child instanceof Directory)
        System.out.println("Deleting directory " + child);
      else if (child instanceof File)
        System.out.println("Deleting file " + child);
      child.delete();
    }
  }

  public void delete() {
    for (FileSystemElement child : children) {
      child.delete();
    }
    super.delete();
  }

  @Override
  public String toString() {
    return "* " + super.toString() + "/";
  }

  @Override
  public FileSystemElement recFind(String name) {
    if (getName().equals(name)) {
      return this;
    }
    for (FileSystemElement child : children) {
      FileSystemElement found = child.recFind(name);
      if (found != null) {
        return found;
      }
    }
    return null;
  }

  @Override
  public void printTree(int level) {
    super.printTree(level);
    for (FileSystemElement child : children) {
      child.printTree(level + 1);
    }
  }

  public void sort() { Collections.sort(children); }
}
