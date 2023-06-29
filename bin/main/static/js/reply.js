const loadList = async(page) => {
  const {data} = await axios.get(`${link}${tno}/list`, {
      params: {
          page: page, // 페이지 번호를 쿼리 매개변수로 전송
          size: 10 // 여기서 페이지 크기를 설정할 수 있습니다.
      }
  });
  console.log(data);
  return data;
}

const registReply = async(list) => {
console.log
const {data} = await axios.post(`${link}${tno}/new`, list)
return data;
}

const deleteReply = async(rno) => {
const {data} = await axios.delete(`${link}${rno}`)
return data;
}

const modifyReply = async(list) => {
const {data} = await axios.put(`${link}update`, list)
return data;
}