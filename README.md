# 비밀 다이어리 어플
NumberPicker 3개로 비밀번호를 입력받고 비밀번호를 어플에 저장하여
EditText내용을 어플에 저장하는 어플.


## 패스워드 변경(changepasswardButton)
changePasswordMode 가 ture 로 바뀌면 비밀번호 변경 모드를 실행하고 다른행동(open버튼) 을 사용 할 수 없도록 해야한다.

1. 패스워드가 올바를때
edit 함수에 저장된 패스워드가 올바르면(equals 함수로 체크) Toast메세지 출력후 패스워드를 입력(numberPicker1.value)받아 
getSharedPreferences 함수를 이용하여 저장


2. 패스워드 변경중 open버튼을 클릭하면
changePasswordMode가 true 일때는 Toast메세지 출력 후 return@setOnClickListener

3. 패스워드가 올바르지 않아 패스워드 변경이 활성화 되지않을때
실패 AlerDialog 출력


## 다이어리 열기(openButton)
1.패스워드가 올바를때
passwordFromUser유저가 입력한 값이 passwordferences에 저장된 일치하면
DiaryActivity를 연결

2.패스워드가 틀릴때
실패 AlerDialog 출력

## 다이어리 저장
1. 다이어리를 getSharedPreferences 함수, edit 함수 를 이용해 저장하고

2. 자동저장
Handler를 메인스레드에 연결해서 새로운 스레드를 관리
postDelayed 함수로 3000Millis 동안 아무런입력이 없다면(removeCallbacks) 에 자동으로 스레드를 활성화.
