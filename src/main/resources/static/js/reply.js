const loadList = async(page) => {
    const {data} = await axios.get(`${link}${tno}/list`);
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