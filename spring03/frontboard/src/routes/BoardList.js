
import axios from 'axios'; // Rest API 호출 핵심 !

// Hook 함수 사용
import React, { useState, useEffect } from 'react';

//Navigation
import { Link } from 'react-router-dom';

function BoardList() {

    // 변수 선언
    const [boardList, setBoardList] = useState([]); // 배열값을 받아서 상태를 저장하기 때문에 [] 사용
    // 함수 선언
    const getBoardList = async () => {
        var pageString = 'page=0';

        try {   // 백엔드 서버가 실행되지 않으면 예외를 발생시킴. AXIOS ERROR
            const resp = (await axios.get("http://localhost:8080/api/board/list/free?" + pageString));

            if(resp.status === 200) {   // 정상(200코드)일 때
                setBoardList(resp.data);
                console.log(resp.data);
            } else if (resp.status === 404) {
                alert("서버가 페이지가 존재하지 않습니다");
            } else if (resp.status === 500) {
                alert("서버 오류 발생.\n 관리자에게 문의바랍니다.");
            }

        } catch (error) {
            console.log("EEEEE :" + error);
            alert("서버가 연결되지 않았습니다.");
        }
    }

    useEffect(() => {
        getBoardList();
    }, []);

    return (
        <div className="container">
            <table className='table'>
                <thead className='table-dark'>
                    <tr className='text-center'>
                        <th>번호</th>
                        <th style={{ width: '50%' }}>제목</th>
                        <th>글쓴이</th>
                        <th>조회수</th>
                        <th>작성일자</th>
                    </tr>
                </thead>
                <tbody>
                    {/* 반복으로 들어갈 부분 */}
                    {boardList.map((board) => (
                        <tr className='text-center' key={board.bno}>
                            <td>{board.bno}</td>
                            <td className='text-start'>{board.title}</td>
                            <td>{board.writer}</td>
                            <td>{board.hit}</td>
                            <td>{board.createDate}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default BoardList;