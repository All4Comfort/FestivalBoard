package com.whiteboard.whiteboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.ReplyDTO;
import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.ReviewReplyRepository;
import com.whiteboard.whiteboard.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewReplyService {

  private final ReviewRepository reviewRepository;
  private final ReviewReplyRepository reviewReplyRepository;

  public Long save(ReplyDTO replyDTO) {

      //부모엔티티(Review) 조회
      Optional<Review> optionalReviewEntity = reviewRepository.findById(replyDTO.getReviewNum());
      if(optionalReviewEntity.isPresent()) {
        Review review = optionalReviewEntity.get();
        //FestivalReply festivalReplyEntity = 
      }
      return null;
  }
    
  // public Long save(CommentDTO commentDTO) {
  //       /* 부모엔티티(BoardEntity) 조회 */
  //       Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
  //       if (optionalBoardEntity.isPresent()) {
  //           BoardEntity boardEntity = optionalBoardEntity.get();
  //           CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
  //           return commentRepository.save(commentEntity).getId();
  //       } else {
  //           return null;
  //       }
  //   }

  //   public List<CommentDTO> findAll(Long boardId) {
  //       BoardEntity boardEntity = boardRepository.findById(boardId).get();
  //       List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
  //       /* EntityList -> DTOList */
  //       List<CommentDTO> commentDTOList = new ArrayList<>();
  //       for (CommentEntity commentEntity: commentEntityList) {
  //           CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
  //           commentDTOList.add(commentDTO);
  //       }
  //       return commentDTOList;
  //   }
}

