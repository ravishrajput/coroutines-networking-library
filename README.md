# coroutines-networking-library
[![ravishrajput](https://img.shields.io/badge/ravishrajput-androiddeveloper-green.svg)](http://ravishrajput.com/)
[![Coroutines](https://img.shields.io/badge/Kotlin-Coroutines-blue.svg)](https://kotlinlang.org/docs/reference/coroutines.html)

<p align="center">
  <img src="/docs/coroutines-networking-library-header.png">
</p>

This library provides handling of exceptions occurred during an API call using Coroutines. Along with exception handling we can monitor error codes specific to HttpException.

### Importing the Library
On your module `build.gradle`, add

    dependencies {
        implementation 'com.ravishrajput:coroutines-networking:0.1.2'
    }

### How to use
To execute API calls:

    val result = callCatching {
        // your api call
    }

To handle API success scenarios:

    result.onSuccess {
        // Handle the data when API call is Success
    }

To handle API failure scenarios:

    result.onFailure {
        // Handle the Show error messages based on type of exception or error code
    }



### License
```
   Copyright (C) 2021 ravishrajput.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```