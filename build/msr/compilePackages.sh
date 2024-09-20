#!/bin/sh

# shellcheck disable=SC3043 # local definitions are not POSIX, but they work in our case nonetheless

if [ ! -x "$SAG_HOME/IntegrationServer/bin/jcode.sh" ]; then
  echo "Build failed - jcode is not available or executable at the path $SAG_HOME/IntegrationServer/bin/jcode.sh"
  exit 1
fi

compileAll() {
  local d
  d="$(pwd)"
  cd "$SAG_HOME/IntegrationServer/bin/" || return 2
  ./jcode.sh all
  cd "$d" || return 3
}

compileAll || return $?
