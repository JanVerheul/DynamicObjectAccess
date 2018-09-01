This repository contains Java functionality for dynamic object access. The class DynaWrap is made according the Adapter design pattern. Wrapping an object in DynaWrap will make properties with JavaBeans getters dynamically available. So getMyProperty() on the wrapped class will be made available as get("myProperty") by DynaWrap. Properties in nested aggregated objects will be made available as well and can be accessed with a straight forward dot separated path notation. So getMyClass1().getMyClass2().getMyProperty() on the wrapped class will be made available as get("myclass1.myClass2.myProperty").

Besides dynamic object access, DynaWrap also supports property discovery. The method call discoverProperties() will deliver a List with all paths that lead to an accessible property on that object, to arbitrary levels of object nesting.

The get(...) method on DynaWrap delivers an object of type Object. If the type of the property to be retrieved is known at compile time, there are convenience methods for all primitive Java types, including the String type. So one can save on an explicit cast with calls getInt(...), getDouble(...), getString(...), etc.