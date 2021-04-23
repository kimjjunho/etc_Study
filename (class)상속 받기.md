# (class)상속 받기

int(변수) = int (필드)

함수 = 메소드

생성자

필드 + 메소드+생성자 = 멤버

class이름 (a) extends + 상속받을 class 이름(상속) 

상속을 해주는 클래스는 부모 

받는 클래스는 자식

객체 생성하는 법

class 클래스 명 = new 클래스이름+() 

클래스이름 + () = 생성자

```
public class ClassA {
    int a =10;
    void method(){
        System.out.println("A class");
    }
}
class MainClass{
    public static void main(String[] args) {
        ClassA a = new ClassA(); //객체를 생성하는 방법
        System.out.println(a.a);
        a.method();
     }
}
```

## 클래스의 구조

오버 라이딩 : 부모 class에 있는 메소드를 자식 class에서 변경 하는 것

오버로딩 :  같은 class에 있는 메소드와 매개변수의 개수 또는 타입이 다르다면 같은 이름을 사용해서 메소드를 정의 할수 있다

## 접근 제한자

- public : 외부의 모든 클래스에서 접근이 가능
- protected : 같은 패키지 내부에 있는 클래스, 하위 클래스(상속받은 경우)에서 접근 가능
- (default) : 패키지 내부에 있는 클래스에서 접근 가능(접근 제한자를 명시하지 않았을 경우 사용됨)
- private : 같은 클래스에서 빼고 접근 불가능

