#!/bin/bash
# List files with relative paths from src/main/kotlin
SELECTED=$(find src/main/kotlin -name '*.kt' | sed 's|^src/main/kotlin/||' | fzf --prompt='Select Kotlin Example: ' --preview 'cat src/main/kotlin/{} | grep -v "^import" | grep -v "^package"')

if [[ -n "$SELECTED" ]]; then
  FILE_PATH="src/main/kotlin/$SELECTED"
  echo "▶ Selected: $SELECTED"
  
  # Extract package name (remove 'package ' and any trailing semicolons or spaces)
  PKG=$(grep -m 1 "^package " "$FILE_PATH" | awk '{print $2}' | sed 's/;//g')

  # Extract simple filename without extension
  FILENAME=$(basename "$SELECTED" .kt)
  
  # Capitalize first letter of filename to get probable class name base
  # (Bash 3.2 compatible way)
  FIRST_CHAR=$(echo "${FILENAME:0:1}" | tr '[:lower:]' '[:upper:]')
  REST_CHARS="${FILENAME:1}"
  CLASS_BASE="${FIRST_CHAR}${REST_CHARS}"
  
  # Check if a class/object with that name exists in the file (simple grep heuristic)
  # We look for "class Name" or "object Name"
  if grep -qE "class\s+$CLASS_BASE|object\s+$CLASS_BASE" "$FILE_PATH"; then
      FINAL_NAME="$CLASS_BASE"
  else
      # Top-level main or functions -> NameKt
      FINAL_NAME="${CLASS_BASE}Kt"
  fi
  
  # Construct FQCN
  if [[ -n "$PKG" ]]; then
      MAIN_CLASS="${PKG}.${FINAL_NAME}"
  else
      MAIN_CLASS="$FINAL_NAME"
  fi
  
  echo "▶ Inferred Main Class: $MAIN_CLASS"
  
  ./gradlew run -PmainClass="$MAIN_CLASS" --console=plain
else
  echo "❌ No selection made."
fi
