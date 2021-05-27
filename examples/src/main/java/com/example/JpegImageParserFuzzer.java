// Copyright 2021 Code Intelligence GmbH
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example;

import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;

// Found https://issues.apache.org/jira/browse/IMAGING-275.
public class JpegImageParserFuzzer {
  public static void fuzzerInitialize() {
    // Only used to verify that arguments are correctly passed down to child processes.
    if (System.getProperty("foo") == null || System.getProperty("bar") == null) {
      // The child process did not have a sufficiently high memory limit,
      // Exit the process with an exit code different from that for a finding.
      System.err.println("ERROR: Did not pass all jvm_args to child process.");
      System.exit(3);
    }
  }

  public static void fuzzerTestOneInput(byte[] input) {
    try {
      new JpegImageParser().getBufferedImage(new ByteSourceArray(input), new HashMap<>());
    } catch (IOException | ImageReadException ignored) {
    }
  }
}
