/*
 *  Copyright 2022 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.backend.wasm.debug.info;

import java.io.PrintStream;

public class DebugInfo {
    private LineInfo lines;
    private ControlFlowInfo controlFlow;
    private int offset;

    public DebugInfo(LineInfo lines, ControlFlowInfo controlFlow, int offset) {
        this.lines = lines;
        this.controlFlow = controlFlow;
        this.offset = offset;
    }

    public LineInfo lines() {
        return lines;
    }

    public ControlFlowInfo controlFlow() {
        return controlFlow;
    }

    public int offset() {
        return offset;
    }

    public void dump(PrintStream out) {
        if (offset != 0) {
            out.println("Code section offset: " + Integer.toHexString(offset));
        }
        if (lines != null) {
            out.println("LINES");
            lines.dump(out);
        }
        if (controlFlow != null) {
            out.println("CONTROL FLOW");
            controlFlow.dump(out);
        }
    }
}
