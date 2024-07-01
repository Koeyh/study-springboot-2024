function CustomButton(props) {

    let heroName = props.data.heroName;
    let isLoggedIn = true;
    let content;


    function handleClick(name) {
        if(isLoggedIn) {
            alert(name + "님 로그아웃 되었습니다.");
        } else {
            alert(name + "님 로그인 되었습니다.");
        }
    }

    // if(isLoggedIn) {
    //     content = <button>Log Out</button>
    // } else {
    //     content = <button>Log In</button>
    // }


    return (
        <>
            {/* {content} */}
            {
                isLoggedIn ? (
                    <button onClick={() => handleClick(heroName)}>Log Out</button>
                ) : (
                    <button onClick={() => handleClick(heroName)}>Log In</button>
                )
            }
        </>
    );
  }

export default CustomButton;    // 외부에서 import 하기 위해서 필수적으로 선언되어야 하는 코드