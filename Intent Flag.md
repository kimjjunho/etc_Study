# Intent Flag

https://kylblog.tistory.com/21?fbclid=IwAR3l8zxq90vHXzXgb3YSmDmLGPSuidYfk3c5Ud4CjQ5-Zn5KtE6MBrHYSKY

https://medium.com/@logishudson0218/intent-flag%EC%97%90-%EB%8C%80%ED%95%9C-%EC%9D%B4%ED%95%B4-d8c91ddd3bfc

### 안드로이드 태스크란(Task)?

- Task는 어플리케이션에서 실행되는 액티비티를 보관하고 관리하며 Stack형태의 연속된 Activity로 이루어진다.

  *stack이란?  https://minosaekki.tistory.com/8

  +task는 어플리케이션에서 실행 되는 액티비티를 기록하는 stack이다

- Flag를 사용하여 Task 내 액티비티의 흐름을 제어할 수 있습니다.

### 1. AndroidManifest에서 사용하기

안드로이드 launchMode 속성을 사용하여 적용할 수 있다. 원하는 액티비티에 적용하면 됨.

```
<activity android:launchMode="singleInstance"
```

**standard **

스택중 어느곳에나 위치 가능하며 여러개의 인스턴스가 생성가능하다.

*인스턴스란 https://victor8481.tistory.com/280

**singleTop**

스택중 어느곳에나 위치 가능하며 여러개의 인스턴스가 생성가능하고 호출한 activity와 현재 최상위 activity가(top activity) 동일한 경우 최상위 activity가 재사용 됩니다.(기존 최상위 activity는 pop)

*스택에 요소를 삽입하는 연산을 push, 삭제 연산을 pop이라고 한다.

*최상위 activity : 처음 실행되는 activity

**singleTask**

루트 액티비티로만 존재하며 하나의 인스턴스만 생성가능하다.(타 task에서 동일 activity 사용불가)다른 액티비티 실행시 동일 Task내에서 실행이 가능하다.

**singleinstance**

루트 액티비티로만 존재하며 하나의 인스턴스만 생성가능하고 태스크내에 해당 액티비티 하나만 속할 수 있어 다른 액티비티를 실행시키면 새로운 Task가 생성되어 (FLAG_ACTIVITY_NEW_TASK와 동일) 그 Task내에 포함된다.

*루트 액티비티 : 사용자가 선택하여 실행되는 최초의 액티비티



### 2.소스코드로 사용하기

```
 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
```

FLAG_ACTIVITY_BROUGHT_TO_FRONT

시스템에서 설정하는 값이며, 같은 task에 Activity가 존재할 경우에 Activity의 실행모드가 singleTask이면 자동으로 설정됨

FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET

테스크가 리셋될 때 플래그가 사용된 액티비티부터 위의 액티비티가 모두 삭제된다(예 : ABCD -> B call -> AB)

FLAG_ACTIVITY_REST_TASK-IF-NEEDED

인텐트를 이용하여 새로운 테스크를 생성하거나 존재하고 있는 테스크를 포그라운드로 가져오는 경우가 아닌 경우에는 아무런 효과가 없는 플래그 적절한 경우에 테스크를 리셋하게 되는데, 테스크의 affinity설정에 맞추어 리셋이 발생하게 됩니다. FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET과 같은 플래그 설정에 맞추어진 특정 처리가 일어나게 됩니다.

FLAG_ACTIVITY_CLEAR_TOP

호출하는 액티비티가 스택에 존재할 경우에, 해당 액티비티를 최상위로 올리면서, 그 위에 존재하던 액티비티들은 모두 삭제를 하는 플래그 예를 들어 ABCDE가 존재하는 상태에서 C를 호출하게 되면 ABC만 남게 됩니다.

FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS

실행되는 액티비티들은 최근 실행목록에 표시가 되게 됩니다. 그러나 이러한 표시를 하지 않길 원하는 액티비티가 존재할 경우에 해당 플래그를 넣어주게 되면, 최근 실행목록에 표시가 되지 않게 됩니다.

FLAG_ACTIVITY_FORWARD_RESULT

startActivityForResult를 이용하여서 Activity를 호출할 경우에, 호출하는 쪽이 아닌 한번 더 거쳐서 Result를 받고 싶을 경우가 있습니다.

이러한 경우에 사용하면 되며, A->B->C 일 경우에 C에서 setResult를 설정하여주고, B에서 finish를 하게 되면 A에서는 C의 값을 받을수 있습니다.

FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY

시스템에 의해서 설정되는 플래그. 최근실행목록에서 실행되게 되면 자동으로 설정됨니다.

FLAG_ACTIVITY_MULTIPLE_TASK

FLAG_ACTIVITY_NEW_TASK와 함께 사용하여야 하며 혼자만 사용할 경우 아무 소용이 없습니다.

두개의 플래그를 동시에 사용할 경우 새로운 태스크는 재활용되지 않고 무조건 새로 생성되며 피호출되는 엑티비티는 이 새로운 태스트의 최상위 엑티비티가 됩니다.

FLAG_ACTIVITY_NEW_TASK

새로운 테스크를 생성하여 그 테스크안에 액티비티를 추가할때 사용합니다. 단, 기존에 존재하는 테스크들중에 생성하려는 액티비티와 동일한 affinity를 가지고 있는 테스크가 있다면 그곳으로 액티비티가 들어가게 됩니다.

하나의 어플리케이션안에서는 모든 액티비티가 기본 affinity를 가지고 같은 테스크안에서 동작하는 것이 기본적이지만, FLAG_ACTIVITY_MULTIPLE_TASK 플래그와 함께 사용 하지 않을 경우 무조건적으로 테스크가 새로 생성되는것은 아님을 주의하여야 합니다.

FLAG_ACTIVITY_NO_ANIMATION

액티비티 전환시 애니메이션을 무시합니다.

FLAG_ACTIVITY_NO_HISTORY

액티비티가 스택에 존재하지 않게 되도록 합니다. 로딩화면등에 이용합니다.

FLAG_ACTIVITY_NO_USER_ACTION

이 플래그가 설정되면 자동적으로 액티미티가 호출될 경우에 자동 호출되는 onUserLeaveHint()가 실행되는 것을 차단합니다.

onUserLeaveHint() 콜백 메서드는 어플리케이션 사용중에 전화가 온다거나 하는 등의 사용자의 액션없이 액티비티가 실행/전환되는 경우에 호출되는 메서드 입니다.

FLAG_ACTIVITY_REORDER_TO_FRONT

이 플래그를 이용하게 되면 호출하려는 Activity가 스택에 존재할 경우에, 최상위로 올려주는 효과를 가지게 됩니다.

예를 들어 ABCDE가 있을 경우 C를 호출하게 되면 ABDEC순서로 정렬이 변경되게 됩니다.

FLAG_ACTIVITY_SINGLE_TOP

호출되는 Activity가 최상위에 존재할 경우에는 해당 Activity를 다시 생성하지 않고, 존재하던 Activity를 다시 사용하게 됩니다.

예를 들면 ABC가 존재하는 경우에 C를 호출하게 되면 기존과 동일하게 ABC가 나오게 됩니다.

3.다른 액티비티 종료하기(플래그 사용X)

플래그로 중복을 방지 할 때 스마트폰에서 onpause상태이면 원래 있던 task 인지를 못하고 새로운 task에 액티비티가 생성되는 경우가 있어 액티비티가 실행 중이면 종료하는 소스코드를 추가하여 중복을 막아주느 방법을 고안.