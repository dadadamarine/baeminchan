# 배민찬 서비스
## 진행 방법
* 배민찬 서비스에 대한 html template은 src/main/resources 디렉토리의 static에서 확인할 수 있다. html template을 통해 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 코드스쿼드의 온라인 코드 리뷰 과정](https://github.com/code-squad/codesquad-docs/blob/master/codereview/README.md)
* [동영상으로 살펴보는 코드스쿼드의 온라인 코드 리뷰 과정](https://youtu.be/a5c9ku-_fok)

# STEP1 학습내용 

## spring security 인증범위 설정
- https://gs.saro.me/dev?page=20&tn=480

## 동기화
- synchronized 의 동작방식, 동기화 블럭, 예제 
    - https://tourspace.tistory.com/54
    
## validation
- 커스텀 password validation annotation 만들기
    - https://memorynotfound.com/custom-password-constraint-validator-annotation/ 
    
- 자바의 커스텀 어노테이션 생성을 위한 메타 어노테이션 설명
    - https://jdm.kr/blog/216
    
## enum 
- entity필드 값으로 사용하기
    - http://woowabros.github.io/tools/2017/07/10/java-enum-uses.html 
    
- enumerated
    - http://tomee.apache.org/examples-trunk/jpa-enumerated/
   
## HttpMessageConverter
- 설명과 컨버터의 종류
    - https://yoojh9.github.io/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-HttpMessageConverter/
    - https://devbox.tistory.com/entry/Spring-RequestBody-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EA%B3%BC-ReponseBody-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EC%9D%98-%EC%82%AC%EC%9A%A9 
    
- @ResponseBody, @RequestBody
    - https://jongmin92.github.io/2018/03/02/Spring/spring-requestbody-responsebody/
    - https://lee-mandu.tistory.com/242

## Jackson 라이브러리
- Jackson에 대한 이해
    - https://mommoo.tistory.com/83
- Jackson으로 컨버팅해서 만드는 DTO에 no args constructor이 필요한 이유에 대한 설명 

    - https://manosnikolaidis.wordpress.com/2015/08/25/jackson-without-annotations/
    - ~~이 글은 나도 다시보고 이해해야겠다.~~

    
## RestTemplate
- TestRestTemplate을 이용한 Rest서비 통합테스트 구현
    - http://blog.saltfactory.net/using-resttemplate-in-spring/ 
    
- RestTemplate의 HttpEntity<T> 객체 이해
    - https://docs.spring.io/spring-framework/docs/5.1.6.RELEASE/javadoc-api/org/springframework/http/HttpEntity.html

## Spring 기본
- 서블릿 컨테이너의 개념 및 웹 동작의 구조
    - https://minwan1.github.io/2017/10/08/2017-10-08-Spring-Container,Servlet-Container/

## SLF4J
- SLF4J를 사용해야 하는 이유
    - https://inyl.github.io/programming/2017/05/05/slf4j.html
- 로깅 전략

## Annotation
- @Interface
    - https://stackoverflow.com/questions/918393/whats-the-difference-between-interface-and-interface-in-java
    - https://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html
    - https://stackoverflow.com/questions/41590543/default-in-interface-in-spring
- @Pattern 정규식
    - https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation

## NotEmpty vs NotNull vs NotBlank
- http://wonwoo.ml/index.php/post/520

## Regex
- 정규식 기본
    - http://www.nextree.co.kr/p4327/
- 기본 정규식 소스
    - https://epthffh.tistory.com/entry/%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EC%A0%95%EA%B7%9C%EC%8B%9D 
    
    
# Javascript 

## JS문법
- 객체 초기자 {} 사용법
    - https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Object_initializer
 
## ajax
- http Head content-type의 의미
    - https://github.com/HomoEfficio/dev-tips/blob/master/Request%20Body%EB%A1%9C%20%EB%B3%B4%EB%82%B4%EC%A7%80%EB%8A%94%20JSON%EC%9D%98%20%ED%96%89%EB%B0%A9%20%EB%B6%88%EB%AA%85.md 
    
- JSON.stringify
    - https://babolsk.tistory.com/1067 
    
- ajax 크게 설명
    - https://poiemaweb.com/js-ajax 
    
- ajax fetch 설명
    - https://gs.saro.me/dev?tn=564
    
    
# STEP2 학습내용 

- Lombok 빌더패턴
    - [빌더 사용 및 기본값 설정방법](https://tomining.tistory.com/180)
    - 의문사항 : 어째서 lombok의 빌더패턴을 사용할땐 Jackson 매핑을 위한 noArgsConstructor을 생성하지 않아도 되는가.
    - 의문사항 : 어째서 @NoArgsConstructor를 사용해주면, @AllArgsConstructor도 같이 붙여햐 하는가.
    
    
- 계층구조의 데이터 (recursive query)
    - [오라클 : START WITH CONNECT BY 구문에 대한 이해](http://playdata.io/tutorials/sql/%EC%98%A4%EB%9D%BC%ED%81%B4-start-with-connect-by)
    - [단일테이블의 Tree 구조를 위한 Self Join](https://eclipse4j.tistory.com/211)
    - [Create a Tree using JPA](https://stackoverflow.com/questions/14388037/create-a-tree-using-jpa)
    
- JPA CASCADE
    - [JPA 영속성 전이 개념](http://wonwoo.ml/index.php/post/1002)
    - [Orphan Removal vs Cascade Delete](https://agritsik.wordpress.com/2015/08/06/orphan-removal-vs-cascade-delete-or-how-to-delete-related-entities/)
    
- Docker Mysql 
    - 한글깨짐현상
        - ~~[컨테이너 생성시 설정으로 해결](http://epr.pe.kr/wordpress/?p=553)~~ -> 컨테이너가 생성되지 않는 에러가 
        - [my.cnf 변경으로 해결](http:s//gcjbro.blogspot.com/2016/11/mysql.html)
            - http://egloos.zum.com/mcchae/v/11250100
            
- Handlebars
    - 2중 반복문 , this 
        - http://kwonnam.pe.kr/wiki/handlebars/handlebars_java
        
    - hbs 파일을 못읽을때
        - handlebars-spring-boot-starter 덕분에 컨트롤러에서 문자열을 반환할때 앞의 path와 뒤의 파일 확장자는 자동으로 지정됩니다. 
          (prefix: src/main/resources/templates, suffix: .hbs) 
        - https://jojoldu.tistory.com/255
        
        
- HandlerMethodArgumentResolver
    - [개념](https://addio3305.tistory.com/75)
    - WebMvcConfigurer를 구현한 config에서 bean으로 등록해야고, 추가해줘야함.
    
- Dispatcher servelet 공부
    - https://mangkyu.tistory.com/13
    - [기타 스프링의 구조에 대해서도 공부하기](https://elfinlas.github.io/2017/12/28/SpringBootInterceptor/)
    
- Interceptor
    - [개념](https://www.journaldev.com/2676/spring-mvc-interceptor-example-handlerinterceptor-handlerinterceptoradapter)
    
- Spybean
    - [개념](https://jojoldu.tistory.com/226)
    
- Column(unique = true)
    - [database 생성시에만 유효](https://stackoverflow.com/questions/30460596/jpa-column-unique-true-what-is-really-point-of-having-unique-attribute)
    - unique하지 않은 데이터가 입력될시 [MySQLIntegrityConstraintViolationException](https://www.baeldung.com/spring-dataIntegrityviolationexception)