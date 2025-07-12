#!/bin/bash
set -e

echo "📦 Checking versionName..."

CURRENT_VERSION=$(grep versionName app/build.gradle.kts | grep -oE '[0-9]+\.[0-9]+\.[0-9]+')

if [ ! -f .version ]; then
  echo "0.0.0" > .version
fi

PREVIOUS_VERSION=$(cat .version)

echo "🕒 Old version: $PREVIOUS_VERSION"
echo "🚀 New version: $CURRENT_VERSION"

version_greater() {
  [ "$(printf '%s\n' "$1" "$2" | sort -V | head -n1)" != "$1" ]
}

if ! version_greater "$PREVIOUS_VERSION" "$CURRENT_VERSION"; then
  echo "❌ Error: versionName ($CURRENT_VERSION) should be bigger then ($PREVIOUS_VERSION)"
  exit 1
fi

echo "✅ Version is ОК. Continue..."