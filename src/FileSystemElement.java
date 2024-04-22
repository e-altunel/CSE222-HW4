package src;

import java.util.Date;

public abstract class FileSystemElement
    implements Comparable<FileSystemElement> {
  private String name;
  private Date creationDate;
  private Directory parent;

  public FileSystemElement(final String name, final Directory parent) {
    this.name = name;
    this.creationDate = new Date();
    this.parent = parent;
  }

  public String getName() { return name; }

  public void setName(final String name) { this.name = name; }

  public Date getCreationDate() { return creationDate; }

  public void setCreationDate(final Date creationDate) {
    this.creationDate = creationDate;
  }

  public Directory getParent() { return parent; }

  public void setParent(final Directory parent) { this.parent = parent; }

  public void printPath() {
    if (parent != null) {
      parent.printPath();
      System.out.print("/" + getName());
    }
  }

  public abstract FileSystemElement find(String[] pathParts);

  public Directory findDirectory(final String[] pathParts) {
    return (Directory)find(pathParts);
  }

  public File findFile(final String[] pathParts) { return (File)find(pathParts); }

  public void delete() {
    if (parent == null)
      return;
    parent.remove(this);
    setParent(null);
  }

  public void move(final Directory newParent) {
    if (parent == null)
      return;
    parent.remove(this);
    setParent(newParent);
    newParent.add(this);
  }

  @Override
  public String toString() {
    return name + " (" + creationDate + ")";
  }

  public abstract FileSystemElement recFind(String name);

  public void printTree(final int level) {
    for (int i = 0; i < level; i++)
      System.out.print("  ");
    System.out.println(this);
  }

  @Override
  public int compareTo(final FileSystemElement other) {
    return name.compareTo(other.getName());
  }
}
