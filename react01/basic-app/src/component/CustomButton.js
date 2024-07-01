function CustomButton() {

    let isLoggedIn = true;
    let content;

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
                    <button>Log Out</button>
                ) : (
                    <button>Log In</button>
                )
            }
        </>
    );
  }

export default CustomButton;