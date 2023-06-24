function makePages(page, size, total){
      
    console.log(page, size, total)
  
    // 11, 21, 31 ......
    const startNum = (Math.ceil(page/10.0) * 10) - 9
  
    console.log(`StartNum ${startNum}`)
  
    let result = ""
  
    console.log("result" , result)
    //이전 페이지
    if(startNum !== 1){
      result += `<li class="page-item"><a class="page-link" href="${startNum-1}">이전</a></li>`
    }
  
    let temp = startNum
    console.log("temp", temp)
    console.log("startNum" , startNum)
    while(true){
  
      if(temp * size > total){
        break;
      }
      console.log(temp)
  
      result +=`<li class="page-item"><a class="page-link" href="${temp}">${temp}</a></li>`
      console.log("result", result)
      temp++;
  
    } 
  
    if(total % (size * 10) === 1){
      result += `<li class="page-item"><a class="page-link" href="${startNum + 10}">다음</a></li>`
    }
  
    console.log(result)
    return result
  }