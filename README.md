# Schedule Management Develop

유저 등록을 통해 회원가입

유저 로그인을 통해 세션 생성하여 로그인 후, 유저 및 일정의 전체 조회, 조회, 수정, 삭제 기능을 수행

유저 로그아웃을 통해 세션 종료

로그인을 했을 때, 로그인한 아이디가 아님에도 불구하고 다른 아이디를 삭제하는 경우가 생겨서 수정 필요



## API 명세서

|기능 | Method | URL | Request | Response | 상태코드|
|-----|--------|-----|---------|----------|----------|
|유저 등록|POST|/users/signup|RequestBody|등록 정보|201:정상등록|
|유저 전체 조회|GET|/users|RequestParam|다건 응답 정보|200:정상조회|
|유저 조회|GET|/users/{id}|RequestParam|단건 응답 정보|200:정상조회|
|유저 수정|PUT|/users/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|유저 이름 수정|PATCH|/users/name/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|유저 이메일 수정|PATCH|/users/email/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|유저 비밀번호 수정|PATCH|/users/password/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|유저 삭제|DELETE|/users/{id}|RequestParam|-|200:정상삭제|
|유저 로그인|POST|/users/login|RequestBody, HttpServletRequest|-|200:인증성공|
|유저 로그아웃|POST|/users/logout|HttpServletRequest|-|200:로그아웃|
|-----|--------|-----|---------|----------|----------|
|세션 확인|GET|/session|HttpServletRequest|세션 정보|200:정상조회|
|-----|--------|-----|---------|----------|----------|
|스케줄 등록|POST|/schedules|RequestBody|등록 정보|201:정상등록|
|스케줄 전체 조회|GET|/schedules|RequestParam|다건 응답 정보|200:정상조회|
|스케줄 조회|GET|/schedules/{id}|RequestParam|단건 응답 정보|200:정상조회|
|스케줄 수정|PUT|/schedules/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|스케줄 제목 수정|PATCH|/schedules/title/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|스케줄 할 일 수정|PATCH|/schedules/toDo/{id}|RequestParam, RequestBody|수정 정보|200:정상수정|
|스케줄 삭제|DELETE|/schedules/{id}|RequestParam|-|200:정상삭제|

![image](https://github.com/user-attachments/assets/26c8fc45-de0e-4b26-a52a-65c6d1c5af9d)
