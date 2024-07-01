// import { useState } from 'react';

function IncButton({ count, onClick }) {
    // const [count, setCount] = useState(0);  // count = "변수", setCount = 변수 값을 조정할 "함수", useState(0) = 최초값 0 초기화
  
    // function upClick() {
    //   setCount(count + 1);
    // }
  
    return (
      <button onClick={onClick}>
        {count}번 증가 !!
      </button>
    )
}
export default IncButton;