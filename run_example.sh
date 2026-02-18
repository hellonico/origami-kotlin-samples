#!/bin/bash
TARGET=$(find src/main/kotlin -name '*.kt' | sed 's|^src/main/kotlin/||' | sed 's|.kt$||' | sed 's|/|.|g' | fzf --prompt='Select Kotlin Example: ' --preview 'cat src/main/kotlin/$(echo {} | sed "s|\.|/|g").kt | grep -v "^import" | grep -v "^package"')

if [[ -n "$TARGET" ]]; then
  echo "▶ Running: $TARGET"
  
  FILE_PATH="src/main/kotlin/$(echo $TARGET | sed 's|\.|/|g').kt"
  
  # Heuristic: If we find a top-level "fun main" (at start of line), assume Kt suffix
  if grep -q "^fun main" "$FILE_PATH" || grep -q "^suspend fun main" "$FILE_PATH"; then
     MAIN_CLASS="${TARGET}Kt"
  else
     MAIN_CLASS="$TARGET"
  fi
  
  echo "  (Inferred Main Class: $MAIN_CLASS)"
  
  ./gradlew run -PmainClass="$MAIN_CLASS" --console=plain
else
  echo "❌ No selection made."
fi
