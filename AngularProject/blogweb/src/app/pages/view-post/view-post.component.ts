import { Component } from '@angular/core';
import { PostService } from '../../service/post.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { error } from 'console';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../../service/comment.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrl: './view-post.component.scss'
})
export class ViewPostComponent {

  postId = this.activatedRouter.snapshot.params['id'];
  postData:any;
  comments:any;
  commentForm!:FormGroup;
  constructor(
    private postService:PostService,
    private activatedRouter: ActivatedRoute,
    private snackBar : MatSnackBar,
    private fb : FormBuilder,
    private commentService:CommentService
  ){}

  ngOnInit() {
    console.log(this.postId);
    this.getPostById();
    this.commentForm = this.fb.group({
      postedBy : [null,Validators.required],
      content : [null , Validators.required]
    })
  }
  getCommentsByPost () {
    return this.commentService.getAllCommentsByPostId(this.postId).subscribe(res => {
      this.comments = res;
    }, error => {
      this.snackBar.open("something went wrong",'ok');
    })
  }
  getPostById() {
    this.postService.getPostById(this.postId).subscribe(
      res => {
        this.postData = res;
        console.log(res);
        this.getCommentsByPost();
      },error => {
        this.snackBar.open("something went wrong","ok");
      }
    )
  }
  likePost() {
    this.postService.likePost(this.postId).subscribe(res => {
      this.snackBar.open("post liked successfully !!!","ok");
      this.getPostById();
    }, error => {
      this.snackBar.open("something went wrong","ok");
    })
  }
  publishComment() {
      const postedBy = this.commentForm.get('postedBy')?.value;
      const conetent = this.commentForm.get('content')?.value;
      this.commentService.createComment(this.postId,postedBy,conetent).subscribe((res)=> {
      this.snackBar.open("comment published successfully",'ok');
      this.getCommentsByPost();
    }, error => {
      this.snackBar.open("Something Went Wrong",'ok');
    })
  }
  
}
