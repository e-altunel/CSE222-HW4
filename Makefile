JC = javac
JFLAGS = -g
SRC_DIR = src
OUT_DIR = bin
SRCS = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(OUT_DIR)/$(SRC_DIR)/%.class,$(SRCS))

all: $(CLASSES) $(OUT_DIR)/Main.class

run: all
	java -cp $(OUT_DIR) Main

$(OUT_DIR)/Main.class: Main.java
	$(JC) $(JFLAGS) -d $(OUT_DIR) $<

$(OUT_DIR)/$(SRC_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) $(JFLAGS) -d $(OUT_DIR) $<

clean:
	rm -rf $(OUT_DIR) doc

doc:
	javadoc -d doc $(SRCS)

.PHONY: all clean doc run