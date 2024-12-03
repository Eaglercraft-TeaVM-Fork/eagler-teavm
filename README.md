# eagler-teavm

### Branch: eagler-r1

Fork of TeaVM for compiling EaglercrafX's WASM GC runtime

#### Changes in this fork:
- Implemented malloc/free in TeaVM's WASM GC backend for allocating direct buffers from a `WebAssembly.Memory`
- Implemented memset and memcpy intrinsic functions
- Made TeaVM's `Address` class work in the WASM GC backend
- Added support for the `@Unmanaged` annotation in the WASM GC backend

#### New API Additions

### `org.teavm.interop.DirectMalloc`
- **`public static native Address malloc(int sizeBytes);`** - Allocates `sizeBytes` of memory from the WebAssembly memory object, if the integer value of the returned address is 0 then the program has run out of memory
- **`public static native Address calloc(int sizeBytes);`** - Allocates `sizeBytes` of memory from the WebAssembly memory object and fills it with zeros, returns 0 if out of memory
- **`public static native void free(Address ptr);`** - Frees an address that was previously allocated with malloc or calloc, bad things will happen if you free an address that was never allocated
- **`public static native void memcpy(Address dst, Address src, int count);`** - Intrinsic, uses the `memory.copy` instruction to efficiently copy `count` bytes from `src` address to `dst` address
- **`public static native void memset(Address ptr, int val, int count);`** - Intrinsic, uses the `memory.fill` instruction to fill `count` bytes at `ptr` address with bytes of `val` value
- **`public static native void zmemset(Address ptr, int count);`** - Intrinsic, uses the `memory.fill` instruction to fill `count` bytes at `ptr` address with zeros

### build.gradle: `teavm.wasmGC` section
- **`directMallocSupport`** - Set to `true` to enable the DirectMalloc class in your build
- **`minHeapSize`** - Initial DirectMalloc heap size in megabytes
- **`maxHeapSize`** - Maximum DirectMalloc heap size in megabytes

You will not recieve support from the developer of TeaVM regarding any issues caused by this fork!

# TeaVM

[![.github/workflows/ci.yml](https://github.com/konsoletyper/teavm/actions/workflows/ci.yml/badge.svg)](https://github.com/konsoletyper/teavm/actions/workflows/ci.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.teavm/teavm-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.teavm/teavm-maven-plugin) 
[![Download](https://teavm.org/maven/latestBadge.svg)](https://teavm.org/maven/_latest)
[![Gitter chat](https://img.shields.io/badge/gitter-join%20chat-green.svg)](https://gitter.im/teavm/Lobby)

See documentation at the [project web site](https://teavm.org/).

Useful links:

* [Getting started](https://teavm.org/docs/intro/getting-started.html)
* [Gallery](https://teavm.org/gallery.html)
* [Site source code repository](https://github.com/konsoletyper/teavm-site)
* [Discussion on Google Groups](https://groups.google.com/forum/#!forum/teavm)


## Building TeaVM

Simply clone source code (`git clone https://github.com/konsoletyper/teavm.git`)
and run Gradle build (`./gradlew publishToMavenLocal` or `gradlew.bat publishToMavenLocal`).
You should build samples separately, as described in [corresponding readme file](samples/README.md).


### Useful Gradle tasks

* `:tools:classlib-comparison-gen:build` &ndash; build Java class library compatibility report.
  result is available at: `tools/classlib-comparison-gen/build/jcl-support`


## Embedding TeaVM

If you are not satisfied with Maven, you can embed TeaVM in your program 
or even create your own plugin for any build tool, like Ant or Gradle.
The starting point for you may be `org.teavm.tooling.TeaVMTool` class from `teavm-tooling` artifact. 
You may want to go deeper and use `org.teavm.vm.TeaVM` from `teavm-core` artifact, learn how `TeaVMTool` initializes it. 
To learn how to use `TeaVMTool` class itself, find its usages across project source code. 
You most likely encounter Maven and IDEA plugins.
  
Please, notice that these APIs for embedding are still unstable and may change between versions.


## WebAssembly

WebAssembly support is in experimental status. It may lack major features available in JavaScript backend. 
There's no documentation yet, and you should do many things by hands 
(like embedding generated `wasm` file into your page, importing JavaScript objects, etc).
Look at [samples/benchmark](https://github.com/konsoletyper/teavm/blob/master/samples/benchmark/) module.
You should first examine `pom.xml` file to learn how to build `wasm` file from Java.
Then you may want to examine `index-teavm.html` and `index-teavm.js`
to learn how to embed WebAssembly into your web page.


## License
 
TeaVM is distributed under [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
TeaVM does not rely on OpenJDK or code or other (L)GPL code.
TeaVM has its own reimplementation of Java class library, which is either implemented from scratch or
based on non-(L)GPL projects:

* [Apache Harmony](https://harmony.apache.org/) (Apache 2.0)
* [Joda-Time](https://github.com/JodaOrg/joda-time) (Apache 2.0)
* [jzlib](https://github.com/ymnk/jzlib) (BSD style license)

If you want to contribute code to implementation of Java class library, 
please make sure it's not based on OpenJDK or other code licensed under (L)GPL.


## Feedback

More information is available at the official site: https://teavm.org.

Ask your questions by email: info@teavm.org. Also, you can report issues on a project's
[issue tracker](https://github.com/konsoletyper/teavm/issues).
