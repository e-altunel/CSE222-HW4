package src;

/**
 * Manages a file system.
 */
public class FileSystemManagement {

  /**
   * Prints the menu and returns the user's choice.
   * @return the user's choice
   */
  private static int printMenu() {
    System.out.println("\033[31m==== File System Management ====\033[0m");
    System.out.println("1. Change directory");
    System.out.println("2. List directory contents");
    System.out.println("3. Create file/directory");
    System.out.println("4. Delete file/directory");
    System.out.println("5. Move file/directory");
    System.out.println("6. Search file/directory");
    System.out.println("7. Print directory tree");
    System.out.println("8. Sort contents by date");
    System.out.println("9. Exit");
    System.out.print("Enter your choice: ");
    return Integer.parseInt(System.console().readLine());
  }

  /**
   * Manages the file system.
   */
  private final FileSystem fs;

  /**
   * Constructs a new FileSystemManagement object.
   */
  public FileSystemManagement() { fs = new FileSystem(); }

  /**
   * Runs the file system management program.
   */
  public void run() {
    boolean exit = false;
    while (!exit) {
      int choice = -1;
      try {
        choice = printMenu();
      } catch (final NumberFormatException e) {
        System.out.println("Invalid choice");
        continue;
      }
      switch (choice) {
      case 1:
        changeDirectory();
        break;
      case 2:
        listDirectoryContents();
        break;
      case 3:
        createFileOrDirectory();
        break;
      case 4:
        deleteFileOrDirectory();
        break;
      case 5:
        moveFileOrDirectory();
        break;
      case 6:
        searchFileOrDirectory();
        break;
      case 7:
        printDirectoryTree();
        break;
      case 8:
        sortContentsByDate();
        break;
      case 9:
        exit = true;
        break;
      default:
        System.out.println("Invalid choice");
      }
    }
  }

  /**
   * Changes the current directory.
   */
  private void changeDirectory() {
    System.out.print("Current directory: ");
    fs.printCurrentPath();
    System.out.print("Enter new directory path: ");
    final String path = System.console().readLine();
    try {
      fs.setCurrentDirectory(path);
      System.out.print("Current directory changed to: ");
      fs.printCurrentPath();
    } catch (final IllegalArgumentException e) {
      System.out.println("Invalid path");
    }
  }

  /**
   * Lists the contents of the current directory.
   */
  private void listDirectoryContents() {
    System.out.print("Listing contents of directory: ");
    fs.printCurrentPath();
    fs.printCurrentChildren();
  }

  /**
   * Creates a file or directory.
   */
  private void createFileOrDirectory() {
    System.out.print("Current directory: ");
    fs.printCurrentPath();
    System.out.print("Enter name: ");
    final String name = System.console().readLine();
    System.out.print("Create file or directory? (f/d): ");
    final String choice = System.console().readLine();
    if (choice.equals("f")) {
      fs.createFile(name);
    } else if (choice.equals("d")) {
      fs.createDirectory(name);
    } else {
      System.out.println("Invalid choice");
    }
  }

  /**
   * Deletes a file or directory.
   */
  private void deleteFileOrDirectory() {
    System.out.print("Current directory: ");
    fs.printCurrentPath();
    System.out.print("Enter name: ");
    final String name = System.console().readLine();
    try {
      fs.delete(name);
      System.out.println("Deleted " + name);
    } catch (final IllegalArgumentException e) {
      System.out.println("Invalid name");
    }
  }

  /**
   * Moves a file or directory.
   */
  private void moveFileOrDirectory() {
    System.out.print("Current directory: ");
    fs.printCurrentPath();
    System.out.print("Enter name: ");
    final String name = System.console().readLine();
    System.out.print("Enter new parent directory path: ");
    final String path = System.console().readLine();
    try {
      fs.move(name, path);
      System.out.println("Moved " + name + " to " + path);
    } catch (final IllegalArgumentException e) {
      System.out.println("Invalid name or path");
    }
  }

  /**
   * Searches for a file or directory.
   */
  private void searchFileOrDirectory() {
    System.out.print("Enter name: ");
    final String name = System.console().readLine();
    final FileSystemElement found = fs.recFind(name);
    if (found != null) {
      System.out.print("Found: ");
      found.printPath();
      System.out.println();
    } else {
      System.out.println("Not found");
    }
  }

  /**
   * Prints the directory tree.
   */
  private void printDirectoryTree() { fs.printTree(); }

  /**
   * Sorts the contents of the current directory by date.
   */
  private void sortContentsByDate() {
    fs.getCurrentDirectory().sort();
    System.out.println("Contents sorted by date");
  }
}
