## MOKO gradle commands
### Sync new resources
```
./gradlew generateMRcommonMain
```
*** This will generate a `MR` class containing `MR.strings.my_string`, which we can use in `commonMain`

## Example strings usage
### Compose
```
val string: String = stringResource(MR.strings.my_string)
```
### Native Android
```
val string = MR.strings.my_string.desc().toString(context = this)
```
### Native iOS
```
let string = MR.strings().my_string.desc().localized()
```
