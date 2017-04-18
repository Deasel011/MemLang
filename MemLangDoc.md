------------**PRECONDITIONS**------------

_**target**_ - loads the target process' virtual memory ranges

_Usage_

    Target = "Foo.exe"

**_size_** - defines the size of the the int in bytes (4 = 32 bits, 8 = 64 bits)

_Usage_

    Size = 4

------------**DATA TYPES**------------

_**field**_ - table of address which represents a concept which wants to be mapped in the memory. Currently, fields can only be matched to 32 or more bit integers.

_Usage_

Declaration : `field foo`

------------**FUNCTIONS**------------

_**find**_ - searches for a value inside the process

_Usage_

    foo = find 65

_**narrow**_ - narrows a field's addresses to further define the address of the targeted value

_Usage_

    foo = narrow 68

_**set**_ - changes a field to a defined value

_Usage_

    set foo = 99

_**print**_ - prints the unique value of a field

_Usage_

    print foo

------------**BLOCKS**------------

_**execute**_ - defines an execute block to be executed on a thread each X miliseconds.

_Usage_

    exec fooblock 1500 { set foo 99 }

_**stop**_ - stops the execution of a block thread

_Usage_

    stop fooblock
