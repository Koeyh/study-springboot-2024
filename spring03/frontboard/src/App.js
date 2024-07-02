import './App.css';

import 'bootstrap/dist/css/bootstrap.min.css';

// 화면 라우팅을 위해 라이브러리 추가
// import 시 {} 괄호 사용시 해당 이름 그대로 사용, 안쓰면 별칭 사용
import { Routes, Route} from 'react-router-dom'
import React from 'react';

// 만든 화면 추가
import Home from './routes/Home';
import BoardList from './routes/BoardList';
import QnaList from './routes/QnaList';
import Login from './routes/Login';


function App() {
  return (
    <Routes>
      {/* a, Link 링크를 누르면 화면 전환 될 페이지 */}
      <Route path='/home' element={<Home/>}></Route>
      <Route path='/boardList' element={<BoardList/>}></Route>
      <Route path='/qnaList' element={<QnaList/>}></Route>
      <Route path='/login' element={<Login/>}></Route>
    </Routes>
  );
}

export default App;