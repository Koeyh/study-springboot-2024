import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

// 화면 전환에 필요한 react-router-dom
import { BrowserRouter } from 'react-router-dom';

// 만든 페이지 추가
import Header from './layout/Header';
import Footer from './layout/Footer';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <div className='App h-full w-full'>
    <BrowserRouter>
      <div id='wrapper' className='flex flex-col h-screen'>
        {/* head */}
        <Header/>
        {/* main-content */}
        <div id='main-content' className='main flex-1'>
          <App/>
        </div>
        {/* footer */}
        <Footer/>
      </div>
    </BrowserRouter>
  </div>
);

