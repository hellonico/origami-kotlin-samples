#!/bin/bash

for i in `find . -name "*.kt"` ; do
  echo -n "## "
  echo $i | rev | cut -d'/' -f 1 | rev

  echo '```java'
  cat $i | grep -v import | grep -v package | grep -v "//" | grep -v -e '^$'
  echo '```'


  # bash splits on space and new lines by default
  IFS=$'\n'       # make newlines the only separator
  set -f          # disable globbing

  INS=`cat $i | grep -v import | grep imread`

  for out in $INS ; do
    img=`echo $out| head -n1 | cut -d"\"" -f2`
    imgt=`echo $img | cut -d"/" -f2`
    echo "##### < $imgt "
    echo "<img src=\"$img\" height=25% width=25%/>"
    echo ""
  done


  OUTS=`cat $i | grep -v import | grep imwrite`

  for out in $OUTS ; do
    img=`echo $out| head -n1 | cut -d"\"" -f2`
    imgt=`echo $img | cut -d"/" -f2`
    echo "##### > $imgt "
    echo "<img src=\"$img\" height=25% width=25%/>"
    echo ""
  done

  echo ""
done