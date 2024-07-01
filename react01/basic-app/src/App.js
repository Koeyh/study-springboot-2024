import logo from './logo.svg';
import './App.css';
import CustomButton from './component/CustomButton';
import { useState } from 'react';
import IncButton from './component/IncButton';

// 데이터 생성시 보통 const 사용
const ironMan = {
  name: 'Tony',
  heroName: 'Iron Man',
  imgUrl: 'https://img.danawa.com/prod_img/500000/207/533/img/18533207_1.jpg?_v=20221226163359',
  imgSize: 100, 
}

const weapons = [
  { title: 'Beam', idx: 1} ,
  { title: 'Blaster', idx: 2},
  { title: 'Missle', idx: 3},
  { title: 'Laser', idx: 4},
]

console.log(weapons);

const listWeapons = weapons.map(weapons =>
  <li key={weapons.idx}>
    {weapons.title}
  </li>
  );

function App() {
  const [count, setCount] = useState(0);

  function handleClick() {
    setCount(count + 1);
  }

  return (
    <div className="App">
      <header className="App-header">
        {/* <h1>Hello React.js !</h1> */}
        <h1>{ironMan.heroName}</h1>
        <img className='profile'
             src={ironMan.imgUrl}
             alt={ironMan.name}
             style={{
              width: ironMan.imgSizem,
              height: ironMan.imgSize,
              // borderRadius: '0%',
             }}
        />
        <IncButton count={count} onClick={handleClick}/>
        <IncButton count={count} onClick={handleClick}> </IncButton>
        {/* <ul>{listWeapons}</ul>
        <CustomButton data={ironMan}/> */}
      </header>    
    </div>
  );
}

export default App;
